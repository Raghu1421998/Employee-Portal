import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';


@Component({templateUrl: 'home.component.html',
styleUrls: ['./home.component.css']})
export class HomeComponent implements OnInit {
    loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;
    loginmessage:string;
    loginm:boolean;
    loginmessage1:string;
    loginm1:boolean;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
       
    ) {
       
    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', [Validators.required]],
            password: ['', [Validators.required]]
        });

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    onSubmit(value:any) {
       if(value['username']==='admin'&& value['password']==='admin')
       {
        this.router.navigate(['summary',{msg:"Login Success"}]);

       }
       else 
       {
        
        this.loginm=true;
        this.loginmessage="Enter Valid Username or Password"

       }
       this.submitted=true;
        if (this.loginForm.invalid) {
            return;
        } 
    }
}
