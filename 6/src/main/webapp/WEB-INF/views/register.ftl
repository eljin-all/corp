<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация пользователя</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background: #f4f6f9; padding: 50px; }
        .card { background: white; max-width: 420px; margin: 0 auto; padding: 30px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        h2 { margin-top: 0; color: #2c3e50; text-align: center; }
        .form-group { margin-bottom: 16px; }
        label { display: block; margin-bottom: 6px; font-weight: 600; color: #34495e; }
        input[type="text"], input[type="password"] { width: 100%; padding: 10px; border: 1px solid #ccd1d9; border-radius: 4px; box-sizing: border-box; }
        .error { color: #e74c3c; font-size: 0.85em; margin-top: 4px; }
        .btn-register { width: 100%; background: #2ecc71; color: white; border: none; padding: 12px; border-radius: 4px; cursor: pointer; font-size: 16px; font-weight: 600; }
        .btn-register:hover { background: #27ae60; }
        .back-link { display: block; text-align: center; margin-top: 18px; color: #7f8c8d; text-decoration: none; }
    </style>
</head>
<body>
<div class="card">
    <h2>Регистрация</h2>
    <form action="${rc.contextPath}/register" method="post">
        <div class="form-group">
            <label>Логин:</label>
            <@spring.bind "user.username"/>
            <input type="text" name="username" value="${spring.status.value!?html}"/>
            <#list spring.status.errorMessages as error><div class="error">${error}</div></#list>
        </div>

        <div class="form-group">
            <label>Пароль:</label>
            <@spring.bind "user.password"/>
            <input type="password" name="password" value=""/>
            <#list spring.status.errorMessages as error><div class="error">${error}</div></#list>
        </div>

        <button type="submit" class="btn-register">Зарегистрироваться</button>
    </form>
    <a class="back-link" href="${rc.contextPath}/login">« Вернуться ко входу</a>
</div>
</body>
</html>