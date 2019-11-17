import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Company } from './company';
import * as $ from 'jquery';
let CompanyListComponent = class CompanyListComponent {
    constructor(_companyService, route, router) {
        this._companyService = _companyService;
        this.route = route;
        this.router = router;
        this.p = 1;
        this.count = 5;
        this.company = new Company();
    }
    ngOnInit() {
        if (this.route.snapshot.paramMap.get('msg') === null) {
            this.getCompany();
        }
        else {
            this.message = this.route.snapshot.paramMap.get('msg');
            this.getCompany();
        }
        $(document).ready(function () {
            setTimeout(fade, 500);
            function fade() {
                $(".success").fadeOut(1000);
            }
        });
    }
    getCompany() {
        this._companyService.getAllCompany()
            .subscribe((companyData) => this.companies = companyData, (error) => {
            console.log(error);
            this.statusMessage = "Problem with service. Please try again later!";
        });
    }
    deleteCompany(cId) {
        if (confirm("Are you sure you want to delete this Employee? ")) {
            this._companyService.deleteCompany(cId)
                .subscribe((response) => {
                console.log(response);
                this.message1 = "Company Details Deleted Succesfully..";
                this.getCompany();
            }, (error) => {
                console.log(error);
                this.statusMessage = "Problem with service. Please try again later!";
            });
            $(document).ready(function () {
                setTimeout(fade, 500);
                function fade() {
                    $(".success1").fadeOut(1000);
                }
            });
        }
        else {
            this.router.navigate(['/summary']);
        }
    }
    getCompanyById(cId) {
        this.router.navigate(['edit/', cId]);
    }
};
CompanyListComponent = tslib_1.__decorate([
    Component({
        selector: 'book-list',
        templateUrl: './companyList.component.html',
        styleUrls: ['./companyList.component.css'],
    })
], CompanyListComponent);
export { CompanyListComponent };
//# sourceMappingURL=companyList.component.js.map