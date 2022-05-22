 <div>
        <label for="user">用户名</label><input type="text" id="user">
    </div>
    <div>
        <label for="password">密码</label><input type="password" id="password">
    </div>
    <div>
        <button id="register">注册</button>
    </div>
</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>
    $(function () {
       $("#register").click(function () {
           $.ajax({
               url:"http://localhost:3000/register",
               type:"POST",
               data:{
                    username:$("#user").val(),
                    password:$("#password").val()
               },
               success:function (res) {
                   alert(res);
               },
               error:function (err) {
                   console.log(err);
               }
           })
       })
    });
</script>