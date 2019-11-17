import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Validators } from '@angular/forms';
let HomeComponent = class HomeComponent {
    constructor(formBuilder, route, router) {
        this.formBuilder = formBuilder;
        this.route = route;
        this.router = router;
        this.loading = false;
        this.submitted = false;
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
    onSubmit(value) {
        if (value['username'] === 'admin' && value['password'] === 'admin') {
            this.router.navigate(['summary', { msg: "Login Success" }]);
        }
        else {
            this.loginm = true;
            this.loginmessage = "Enter Valid Username or Password";
        }
        this.submitted = true;
        if (this.loginForm.invalid) {
            return;
        }
    }
};
HomeComponent = tslib_1.__decorate([
    Component({ templateUrl: 'home.component.html',
        styleUrls: ['./home.component.css'] })
], HomeComponent);
export { HomeComponent };
//# sourceMappingURL=home.component.js.map