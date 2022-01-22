import axios from 'axios'
import store from './store'
import router from './router'
import ElenmentUI from 'element-ui'



axios.defaults.baseURL="http://localhost:8081"
//前置拦截器
axios.interceptors.request.use(config =>{
    return config
})
//后置拦截
axios.interceptors.response.use(response =>{
   let resp=response.data
   console.log("==============")
    console.log(resp)

    return response
 },
 error =>{
     console.log("=============")
    console.log(error)
    if(error.response.data){
        error.message = error.response.data.message
    }
    if(error.response.status===401){
        store.commit("REMOVE_INFO")
        router.push("/login")
    }
    ElenmentUI.Message.error(error.message,{duration:3*1000})
    return Promise.reject(error)
}
)