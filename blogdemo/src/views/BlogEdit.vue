<template>
  <div>
  <Header></Header>
  <div class="mydiv">
   <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" >
  <el-form-item label="标题" prop="title">
    <el-input v-model="ruleForm.title"></el-input>
  </el-form-item>
  
  <el-form-item label="摘要" prop="summary">
    <el-input type="textarea" v-model="ruleForm.summary"></el-input>
  </el-form-item>

 <el-form-item label="内容" prop="content">
    <mavon-editor v-model="ruleForm.content"></mavon-editor>
  </el-form-item>

  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
  </el-form-item>
</el-form>
  </div>
  </div>
</template>

<script>
import Header from '../components/Header.vue'
export default {
    name:'BlogEdig',
    components:{Header},
     data() {
      return {
        ruleForm: {
          id:'',
          title: '',
          summary: '',
         content: '',
          
        },
        rules: {
          title: [
            { required: true, message: '请输入标题', trigger: 'blur' },
            { min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur' }
          ],
          summary: [
            { required: true, message: '请输入摘要', trigger: 'blur' }
          ],
          content: [
            {  required: true, message: '请输入内容', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
            this.$axios.post("/blog/edit",this.ruleForm,{
              headers:{
                "Authorization":localStorage.getItem("token")
              }
            }).then(res =>{
              console.log(res)
               this.$alert('操作成功', '提示', {
          confirmButtonText: '确定',
          callback: action => {
            this.$router.push("/blogs")
            console.log(action)
          }
          });
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    },
    created(){
        const blogid= this.$route.params.blogid
       if(blogid){
         this.$axios.get("/blog/"+blogid).then(res =>{
          const blog= res.data.data
          this.ruleForm.id=blog.id
          this.ruleForm.title=blog.title
          this.ruleForm.content=blog.content
          this.ruleForm.summary=blog.summary

         })
       }

    }
  }
</script>

<style>

</style>