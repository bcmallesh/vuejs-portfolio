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
            path: "/digital",
            name: "Digital",
            component: () => import("./components/Digital"),
        },
        {
            path: '/description/',
            name: "Description",
            component: () => import("./components/Description"),
            props: true,
        },
        {
            path: "/login",
            name: "Login",
            component: () => import("./components/Login"),
        },
       
        {
            path: "/projects",
            name: "ProjectList",
            component: () => import("./components/ProjectList"),
            beforeEnter: (to, from, next) => {
                if (AuthenticationService.isUserLoggedIn()) {
                    next()
                } else {
                    next({ path: '/login'})
                }
            }
        },

       
        {
            path: "/createproject",
            name: "CreateProject",
            component: () => import("./components/CreateProject"),
            beforeEnter: (to, from, next) => {
                if (AuthenticationService.isUserLoggedIn()) {
                    next()
                } else {
                    next({ path: '/login'})
                }
            }
        },
        {
            path: "/editproject/:id",
            name: "EditProject",
            component: () => import("./components/EditProject"),
            beforeEnter: (to, from, next) => {
                if (AuthenticationService.isUserLoggedIn()) {
                    next()
                } else {
                    next({ path: '/login'})
                }
            }
        },
        {
            path: "/deleteproject/:id",
            name: "DeleteProject",
            component: () => import("./components/DeleteProject"),
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
                next({ path: '/'})
            },

        },


    ]
});

export default router;