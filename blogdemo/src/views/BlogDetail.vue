<template>
  <div>
      <Header></Header>
      <div class="mblog">
        <h2>{{blog.title}}</h2>
         <el-link icon="el-icon-edit" >
           <router-link :to="{name:'Edit',parame:{params:blogid}}">
            编辑
           </router-link>
           </el-link>
          <el-divider></el-divider>
        <div class="markdown-body" v-html="blog.content"></div>
      </div>
  </div>
</template>

<script>
import Header from '../components/Header.vue'
import 'github-markdown-css/github-markdown.css'
export default {
    name:"BlogDetail",
    components:{Header},
    data(){
      return{
        blog:{
          id:'',
          title:"",
          content:""
        },
       
      }
    },
    created(){
      const blogid =this.$route.params.blogid
       
    if(blogid){
      this.$axios.get("/blog/"+blogid).then(res =>{
       const blog= res.data.data
      this.blog.id=blog.id
      this.blog.title=blog.title

      var md = require('markdown-it')();
   
    var result=md.render(blog.content);
 
      this.blog.content=result

   
      })

    }

    }
}
</script>

<style>
.mblog{
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  min-height: 700px;
  width: 100%;
 padding: 20px 10px;
}
</style>