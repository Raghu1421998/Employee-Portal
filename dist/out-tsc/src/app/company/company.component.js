import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Company } from './company';
let CompanyComponent = class CompanyComponent {
    constructor(_companyService, route, router, formBuilder, location) {
        this._companyService = _companyService;
        this.route = route;
        this.router = router;
        this.formBuilder = formBuilder;
        this.location = location;
        this.services = [];
        this.servicesList = ['Medical', 'Financial', 'Education', 'Marketing', 'Hospitals', 'Banking'];
        this.selectedServices = [];
        this.company = new Company();
        this.businessType = [
            { id: 1, name: 'IT Software' },
            { id: 2, name: 'IT Hardware' },
            { id: 3, name: 'BPO' },
            { id: 4, name: 'Agriculture' },
            { id: 5, name: 'Banking' },
            { id: 6, name: 'Financial Services' }
        ];
        this.countryList = [
            { name: 'US', cities: ['New York', 'Chicago'] },
            { name: 'UK', cities: ['London', 'Manchester'] },
            { name: 'INDIA', cities: ['Bangalore', 'Delhi'] },
        ];
    }
    changeCountry(count) {
        this.company.city = "";
        this.cities = this.countryList.find(con => con.name == count).cities;
    }
    changeCountry1(count) {
        this.cities = this.countryList.find(con => con.name == count).cities;
    }
    ngOnInit() {
        if (this.route.snapshot.paramMap.get('id') === null) {
        }
        else {
            this.cid = this.route.snapshot.paramMap.get('id');
            this.getCompanyById(this.cid);
        }
        this.getCompany();
    }
    getCompany() {
        this._companyService.getAllCompany()
            .subscribe((companyData) => this.companies = companyData, (error) => {
            console.log(error);
            this.statusMessage = "Problem with service. Please try again later!";
        });
    }
    onSubmit(f, value) {
        if (!f.valid && (value['Medical'] === false && value['Financial'] === false && value['Education'] === false && value['Marketing'] === false && value['Hospitals'] === false && value['Banking'] === false)) {
            this.duplicateServiceShow = true;
            this.duplicateServiceMessage = "Please choose Servicing Industry";
        }
        else if (f.invalid) {
        }
        else if (value['Medical'] === false && value['Financial'] === false && value['Education'] === false && value['Marketing'] === false && value['Hospitals'] === false && value['Banking'] === false) {
            this.duplicateServiceShow = true;
            this.duplicateServiceMessage = "Please choose Servicing Industry";
        }
        else {
            this.duplicateServiceShow = false;
            this.serv = true;
            this.duplicateServiceMessage = "";
            this.addCompany();
        }
    }
    goBack() {
        this.location.back();
    }
    addCompany() {
        this.company.serviceslist = this.getServices();
        this._companyService.addCompany(this.company)
            .subscribe((response) => {
            console.log(response);
            this.reset();
            this.router.navigate(['/summary', { msg: this.getMessage() }]);
            this.getCompany();
        }, (error) => {
            console.log(error);
            this.statusMessage = "Problem with service. Please try again later!";
        });
    }
    reset() {
        this.company.id = null;
        this.company.name = null;
        this.company.businessType = null;
        this.company.empSize = null;
        this.company.comType = null;
        this.company.emailAddress = null;
        this.company.country = null;
        this.company.city = null;
        this.company.dateOfJoining = null;
        this.company.dateOfResigning = null;
        this.company.serviceslist = null;
        this.company.services = null;
    }
    getCompanyById(cId) {
        if (cId === 0) {
            this.company == null;
        }
        else {
            this._companyService.getCompanyById(cId)
                .subscribe((companyData) => {
                this.company = companyData;
                this.changeCountry1(this.company.country);
                this.serviceValue = this.company.serviceslist.split(",");
                for (var i = 0; i < this.serviceValue.length; i++) {
                    this.services.push(this.serviceValue[i]);
                }
                console.log(this.services);
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
                    };
            this.reset();
        }
    }
    isSelected(value) {
        return this.selectedServices.indexOf(value) >= 0;
    }
    getServices() {
        var skill = "";
        for (var i = 0; i < this.services.length - 1; i++) {
            skill += this.services[i] + ",";
        }
        skill += this.services[this.services.length - 1];
        return skill;
    }
    getServicesValues(event, value) {
        console.log(event.target.checked);
        if (event.target.checked) {
            this.services.push(value);
        }
        if (!event.target.checked) {
            let index = this.services.indexOf(value);
            if (index > -1) {
                this.services.splice(index, 1);
            }
        }
        console.log(this.services);
        if (this.services.length >= 1) {
            this.duplicateServiceShow = true;
            this.duplicateServiceMessage = "";
        }
        else {
            this.duplicateServiceShow = true;
            this.duplicateServiceMessage = "Please choose Servicing Industry";
        }
    }
    getMessage() {
        return this._companyService.getShowMessage();
    }
};
CompanyComponent = tslib_1.__decorate([
    Component({
        selector: 'company.component',
        templateUrl: './company.component.html',
        styleUrls: ['./company.component.css']
    })
], CompanyComponent);
export { CompanyComponent };
//# sourceMappingURL=company.component.js.map