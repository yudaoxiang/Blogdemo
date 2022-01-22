
import VueRouter from 'vue-router'
import Login from '../views/Login'
import Blogs from '../views/Blogs'
import BlogDetail from '../views/BlogDetail'
import BlogEdit from '../views/BlogEdit'




const router= new VueRouter({
    mode:'history',
    routes:[
        {
            path:'/login',
            name:'Login',
            component:Login
        },
        {
            path:'/',
            name:'Index',
            redirect:{name:"Blogs"}
        },
        {
            path:'/blogs',
            name:'Blogs',
            component:Blogs
        },
        {
            path:'/blog/add',
            name:'Editadd',
            component:BlogEdit,
            meta:{
                requireAuth:true
            }
        },
        {
            path:'/blog/:blogid',
            name:'Detail',
            component:BlogDetail
        },
        {
            path:'/blog/:blogid/edit',
            name:'Edit',
            component:BlogEdit,
            meta:{
                requireAuth:true
            }
        }
    ]

})

export default router
