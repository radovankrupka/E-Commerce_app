<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Login Page</title>
</head>
<body>

<section class="vh-30 gradient-custom" >

    <div class="container py-5 h-30">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <form class="vh-30 gradient-custom" action="Home" method="POST">
                            <div class="mb-md-5 mt-md-4 pb-5">

                            <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                            <p class="text-white-50 mb-5">Please enter your login and password!</p>

                            <div class="form-outline form-white mb-4">
                                <input type="text" name="email" id="email" class="form-control form-control-lg" required/>
                                <label class="form-label" for="email">Email</label>
                            </div>

                            <div class="form-outline form-white mb-4">
                                <input type="password" name="password" id="password" class="form-control form-control-lg" required />
                                <label class="form-label" for="password">Password</label>
                            </div>

                            <p class="small mb-5 pb-lg-2">
                                Janko123  | pouz1 <br>
                                Ferko123  | pouz2 <br>
                                Martin123 | pouz3 <br></p>

                            <input type="hidden" name="operacia" value="login">
                            <button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>

                            </div>
                        </form>


                        <form action="register" method="post">
                            <button class="btn btn-outline-light btn-lg px-5" type="submit" >Register</button>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>


</section>

<br><br>



<%--
<form action="Home" method="POST">
    NICKNAME:      <input type="text" name="nickname"> <br> <br>
    PASSWORD:           <input type="password" name="pwd"> <br>  <br> <br>

    <input type="hidden" name="operacia" value="login">
    <input type="submit" value="SUBMIT">
</form>
<br>
<br>
<br>
--%>

<%--

VALID CREDENTIALS: <br>
NICKNAME  | PWD  <br>
Janko123  | pouz1 <br>
Ferko123  | pouz2 <br>
Martin123 | pouz3 <br>
Ignac123  | pouz4 <br>
Spekulant123 | spek1 <br>
--%>


</body>
</html>