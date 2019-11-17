import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Company } from './company';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class CompanyService{
    message:string;
    constructor(private _httpService: Http){}

    getShowMessage()
    {
        return this.message;
    }
    getAllCompany(): Observable<Company[]>{
        return this._httpService.get("http://localhost:8081/MyBatisCrud1/getall")
                .map((response: Response) => response.json())
                .catch(this.handleError);
    }

    getCompanyById(cId: number): Observable<Company>{
        return this._httpService.get("http://localhost:8081/MyBatisCrud1/getbyid/"+cId)
                .map((response: Response) => response.json())
                .catch(this.handleError);
    }

    addCompany(company: Company){
     

        let body = JSON.parse(JSON.stringify(company));
        console.dir(body);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
       
        if(company.id)
        {
            this.message="Company Details Updated Succesfully";
            return this._httpService.put("http://localhost:8081/MyBatisCrud1/updateall/"+company.id, body, options);

        }else{
            console.log(company);
            this.message="Company Details Inserted Succesfully";

            return this._httpService.post("http://localhost:8081/MyBatisCrud1/insertall", body, options);

        }
    }

    deleteCompany(cId: number){

        return this._httpService.delete("http://localhost:8081/MyBatisCrud1/deleteall/"+cId);
    }

    private handleError(error: Response){
        return Observable.throw(error);
    }
   
}