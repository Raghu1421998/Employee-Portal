import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { CompanyService } from './company.service';
import { Company} from './company';
import { Router, ActivatedRoute } from '@angular/router';
import * as $ from 'jquery';


@Component({
    selector: 'book-list',
    templateUrl: './companyList.component.html',
    styleUrls: ['./companyList.component.css'],
    
})
export class CompanyListComponent implements OnInit{
    p: Number = 1;
    count: Number = 5;
    company = new Company();
    statusMessage: string;
    message: string;
    message1: string;
    companies: Company[];
    constructor(private _companyService: CompanyService,
        private route: ActivatedRoute,
        private router: Router,
   ){}
   key: string = 'name'; //set default
  reverse: boolean = false;
  sort(key){
    this.key = key;
    this.reverse = !this.reverse;
  }
    
                ngOnInit() 
                {
                  if(this.route.snapshot.paramMap.get('msg') === null)
                  {

                    this.getCompany();
                  }
                  else
                  {
                    this.message = this.route.snapshot.paramMap.get('msg');
                    this.getCompany();
                  }
                  $(document).ready(function(){
                    setTimeout(fade, 500);
                      function fade() {
                      $(".success").fadeOut(1000);
                       }
                       
                      
                  });                  
                }

    getCompany(): void{
        this._companyService.getAllCompany()
            .subscribe((companyData) => this.companies = companyData,
            (error) =>{
                console.log(error);
                this.statusMessage = "Problem with service. Please try again later!";
            }
        );
    }
    deleteCompany(cId: number){
        if(confirm("Are you sure you want to delete this Employee? ")) {
          this._companyService.deleteCompany(cId)
          .subscribe((response) => {console.log(response);
            this.message1="Company Details Deleted Succesfully..";
            this.getCompany();
          },
          (error) =>{
              console.log(error);
              this.statusMessage = "Problem with service. Please try again later!";
          });
          $(document).ready(function(){
            setTimeout(fade, 500);
              function fade() {
              $(".success1").fadeOut(1000);
               }
          });
        }
        else
        {
          this.router.navigate(['/summary']);
        }
       
    }
    
    getCompanyById(cId: number)
  {
    
    this.router.navigate(['edit/', cId]);
  }
}