import Vue from "vue";
import Router from "vue-router";
import AuthenticationService from "./service/AuthenticationService";

Vue.use(Router);

const router = new Router({
    mode: 'history', // Use browser history
    routes: [
        {
            path: "/",
            name: "Designs",
            component: () => import("./components/Designs"),
        },
        {
            path: "/login",
            name: "Login",
            component: () => import("./components/Login"),
        },
        {
            path: "/projects",
            name: "Projects",
            component: () => import("./components/Projects"),
            beforeEnter: (to, from, next) => {
                if (AuthenticationService.isUserLoggedIn()) {
                    next()
                } else {
                    next({ path: '/login'})
                }
            }
        },
        {
            path: "/logout",
            name: "Logout",
            component: () => import("./components/Logout"),
            beforeEnter: (to, from, next) => {
                if (AuthenticationService.isUserLoggedIn()) {
                    AuthenticationService.logout();
                }
                next();
            },

        },
    ]
});

export default router;