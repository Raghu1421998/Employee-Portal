import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
let CompanyService = class CompanyService {
    constructor(_httpService) {
        this._httpService = _httpService;
    }
    getShowMessage() {
        return this.message;
    }
    getAllCompany() {
        return this._httpService.get("http://localhost:8080/MyBatisCrud1/getall")
            .map((response) => response.json())
            .catch(this.handleError);
    }
    getCompanyById(cId) {
        return this._httpService.get("http://localhost:8080/MyBatisCrud1/getbyid/" + cId)
            .map((response) => response.json())
            .catch(this.handleError);
    }
    addCompany(company) {
        let body = JSON.parse(JSON.stringify(company));
        console.dir(body);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        if (company.id) {
            this.message = "Company Details Updated Succesfully";
            return this._httpService.put("http://localhost:8080/MyBatisCrud1/updateall/" + company.id, body, options);
        }
        else {
            console.log(company);
            this.message = "Company Details Inserted Succesfully";
            return this._httpService.post("http://localhost:8080/MyBatisCrud1/insertall", body, options);
        }
    }
    deleteCompany(cId) {
        return this._httpService.delete("http://localhost:8080/MyBatisCrud1/deleteall/" + cId);
    }
    handleError(error) {
        return Observable.throw(error);
    }
};
CompanyService = tslib_1.__decorate([
    Injectable()
], CompanyService);
export { CompanyService };
//# sourceMappingURL=company.service.js.map