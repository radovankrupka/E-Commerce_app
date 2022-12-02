<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>

<section class="vh-30 gradient-custom" >

    <div class="container py-5 h-30">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <form class="vh-30 gradient-custom" action="register" method="POST">
                            <div class="mb-md-5 mt-md-4 pb-5">

                                <h2 class="fw-bold mb-2 text-uppercase">Registration</h2>
                                <p class="text-white-50 mb-5">Please enter your new login (email) and password!</p>


                                <div class="form-outline form-white mb-4">
                                    <input type="email" name="email" id="email" value="${fn:escapeXml(param.email)}" class="form-control form-control-lg" required/>
                                    <label class="form-label" for="email">Email</label>
                                    <span class="error">${messages.error}</span>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="password" name="password" id="password" class="form-control form-control-lg" required/>
                                    <label class="form-label" for="password">Password</label>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="text" name="surrname" id="surrname" class="form-control form-control-lg" required/>
                                    <label class="form-label" for="surrname">First Name</label>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="text" name="last_name" id="last_name" class="form-control form-control-lg" required/>
                                    <label class="form-label" for="last_name">Last Name</label>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="text" name="address" id="address" class="form-control form-control-lg" required/>
                                    <label class="form-label" for="address">Address</label>
                                </div>


                                <input type="hidden" name="operacia" value="register">
                                <button class="btn btn-outline-light btn-lg px-5" type="submit">Create account</button>
                                <br/> <br/>
                                <a href="home"> Back to login </a>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>
