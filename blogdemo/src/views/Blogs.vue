<template>
      <div>
        <Header></Header>
<div class="block">
  <el-timeline>
    <el-timeline-item :timestamp="blog.createtime" placement="top"  v-for="blog in blogs" :key="blog.id">
      <el-card>
        <h4>
          <router-link :to="{name:'Detail',params:{blogid:blog.id}}">
             {{blog.title}}
          </router-link>
        </h4>
       
        <p> {{blog.summary}}</p>
      <p >更新时间{{blog.updatetime}}</p>
      </el-card>
    </el-timeline-item>
    
  </el-timeline>
  <el-pagination class="mypage"
  background
  layout="prev, pager, next"
  :current-page	="currentPage"
  :page-size="pageSize"
  :total="total"
  @current-change	 = page
  >
</el-pagination>
</div>
</div>
  
</template>

<script>
import Header from '../components/Header.vue'
export default {
    name:"Blogs",
    components:{Header},
    data(){
      return {
        blogs:{},
        total: 0,
        pageSize:2,
       currentPage:1
      }
    },
    methods:{
      page(currentPage,){
          this.$axios.get("/blogs?pageNum="+currentPage+"&pageSize=4").then(res=>{
            //console.log(res.data.data.list)
              this.blogs=res.data.data.list
              this.currentPage=res.data.data.pageNum
              this.pageSize=res.data.data.pageSize
              this.total=res.data.data.total
             // console.log(this.blogs)
          })
      }
    },
    created(){
      this.page(1,2)
    }


}
</script>

<style>
.mypage{
  margin: 0 auto;
  text-align: center;
}

</style>