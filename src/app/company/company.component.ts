import {Component, OnInit} from '@angular/core';
import {CompanyService} from './company.service';
import {Company} from './company';
import { businessTypeArray } from './businessTypeArray';
import { FormGroup, FormArray, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import {Location, LocationStrategy, PathLocationStrategy} from '@angular/common';
@Component({
    selector: 'company.component',
    templateUrl: './company.component.html',
    styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit{
 
  cid:any;
  serviceValue : Array<string>;
  services : Array<string> = [];
  serv:boolean;
  servicesList : Array<string> = ['Medical', 'Financial', 'Education', 'Marketing','Hospitals','Banking'];
  selected : any;
  selectedServices : Array<string> = [];
  showMessage : boolean;
  duplicateServiceShow : boolean;
  duplicateServiceMessage : string;
  companies: Company[];
  statusMessage: string;
  company = new Company();
  cities: Array<any>;
  businessType:businessTypeArray [] = [
    { id: 1, name: 'IT Software' },
    { id: 2, name: 'IT Hardware' },
    { id: 3, name: 'BPO' },
    { id: 4, name: 'Agriculture' },
    { id: 5, name: 'Banking' },
    { id: 6, name: 'Financial Services' }
  ];
  countryList: Array<any> = [
    { name: 'US', cities: ['New York', 'Chicago'] },
    { name: 'UK', cities: ['London', 'Manchester'] },
    { name: 'INDIA', cities: ['Bangalore', 'Delhi'] },
  ];
 
  changeCountry(count) 
  {
    
    this.company.city=this.countryList.find(con => con.name == count).cities[0];
    this.cities = this.countryList.find(con => con.name == count).cities;
 
  }
  changeCountry1(count) 
  {
    
    this.cities = this.countryList.find(con => con.name == count).cities;
  
  }
       
    constructor(
      private _companyService: CompanyService,
      private route: ActivatedRoute,
      private router: Router,
      private formBuilder: FormBuilder,
      private location: Location
    ){}
   
    ngOnInit():void {
     
      if(this.route.snapshot.paramMap.get('id') === null)
      {
  
      }
      else
      {
        this.cid = this.route.snapshot.paramMap.get('id');
        this.getCompanyById(this.cid );
      }
        this.getCompany();
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
    onReset(f:FormGroup,value : any)
    {
    
     this.duplicateServiceShow=true;
    this.duplicateServiceMessage="";
    }
   
    onSubmit(f:FormGroup,value : any)
    {
      
      
         if(!f.valid && (value['Medical']===false&&value['Financial']===false&&value['Education']===false&&value['Marketing']===false&&value['Hospitals']===false&&value['Banking']===false))
        {
          this.duplicateServiceShow=true;
          this.duplicateServiceMessage="Please choose Servicing Industry";
        }
        else if(value===null)
        {
          this.duplicateServiceShow=true;
          this.duplicateServiceMessage="Please choose Servicing Industry";
        }
        else if(value['Medical']===null&&value['Financial']===null&&value['Education']===null&&value['Marketing']===null&&value['Hospitals']===null&&value['Banking']===null)
        {
          this.duplicateServiceShow=true;
          this.duplicateServiceMessage="Please choose Servicing Industry";
        }
        else if(f.invalid)
        {
        }
       
        else if(value['Medical']===false&&value['Financial']===false&&value['Education']===false&&value['Marketing']===false&&value['Hospitals']===false&&value['Banking']===false)
        {
          this.duplicateServiceShow=true;
          this.duplicateServiceMessage="Please choose Servicing Industry";
        }
        
        
         else
        {
          this.duplicateServiceShow=false;
          this.serv=true;
          this.duplicateServiceMessage="";
          this.addCompany();
        }
          }   
          
          goBack()
          {
            this.location.back();
          }
    addCompany(): void{
      
        this.company.serviceslist = this.getServices();
        this._companyService.addCompany(this.company)
            .subscribe((response) => {console.log(response); 
                     this.reset();
              this.router.navigate(['/summary',{msg: this.getMessage()}]);
              this.getCompany();
          },
            (error) =>{
                console.log(error);
                this.statusMessage = "Problem with service. Please try again later!";
            }
        );   
      }

     
      private reset(){
      
        this.company.name = null;
        this.company.businessType = null;
        this.company.empSize = null;
        this.company.comType = null;
        this.company.emailAddress = null;
        this.company.country = null;
        this.company.city = null;
        this.company.dateOfJoining = null;
        this.company.dateOfResigning = null;

    }

   

    getCompanyById(cId: number){
      if(cId===0)
      {
        this.company==null;
      }
      else
      {
        this._companyService.getCompanyById(cId)
            .subscribe((companyData) => {      
              this.company = companyData; 
              this.changeCountry1(this.company.country);
              this.serviceValue = this.company.serviceslist.split(",");
              for(var i=0; i<this.serviceValue.length; i++)
              {
                this.services.push(this.serviceValue[i]);
              }
              this.selected = this.serviceValue[0];
              this.selectedServices.push(this.serviceValue[0]);
              this.selectedServices.push(this.serviceValue[1]);
              this.selectedServices.push(this.serviceValue[2]);
              this.selectedServices.push(this.serviceValue[3]);
              this.selectedServices.push(this.serviceValue[4]);
              this.selectedServices.push(this.serviceValue[5]);
              this.getCompany(); 
            })
            ,
            (error) => {
                console.log(error);
                this.statusMessage = "Problem with service. Please try again later!";
            }
            this.reset();
      }
     
    }
    isSelected(value: string): boolean 
    {
      return this.selectedServices.indexOf(value) >= 0;
    }
  
    getServices()
    {
      var service= "";
    for(var i=0; i<this.services.length-1; i++)
    {
      service+=this.services[i]+",";
    }
    service+=this.services[this.services.length-1];
    
    return service;
    }
    getServicesValues(event, value)
    {
      console.log(event.target.checked);
      if(event.target.checked)
      {
      
        this.services.push(value);
      }
      if(!event.target.checked)
      {
        let index = this.services.indexOf(value);
        if(index > -1)
        {
          this.services.splice(index, 1);
        }
      }
      if(this.services.length>=1)
      {
        this.duplicateServiceShow=true;
        this.duplicateServiceMessage="";
       
      }
      else
      {
        this.duplicateServiceShow=true;
        this.duplicateServiceMessage="Please choose Servicing Industry";
        
      }
    }
    getMessage()
    {
      return this._companyService.getShowMessage();
    }
    
}