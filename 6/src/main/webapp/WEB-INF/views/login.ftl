<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход в систему</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background: #f4f6f9; padding: 50px; }
        .card { background: white; max-width: 420px; margin: 0 auto; padding: 30px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        h2 { margin-top: 0; color: #2c3e50; text-align: center; }
        .form-group { margin-bottom: 16px; }
        label { display: block; margin-bottom: 6px; font-weight: 600; color: #34495e; }
        input[type="text"], input[type="password"] { width: 100%; padding: 10px; border: 1px solid #ccd1d9; border-radius: 4px; box-sizing: border-box; }
        .btn-login { width: 100%; background: #3498db; color: white; border: none; padding: 12px; border-radius: 4px; cursor: pointer; font-size: 16px; font-weight: 600; }
        .btn-login:hover { background: #2980b9; }
        .alert-error { background: #fdf2f2; color: #e74c3c; padding: 10px; border-radius: 4px; margin-bottom: 15px; text-align: center; }
        .alert-success { background: #eefaf3; color: #27ae60; padding: 10px; border-radius: 4px; margin-bottom: 15px; text-align: center; }
        .register-link { display: block; text-align: center; margin-top: 18px; color: #3498db; text-decoration: none; }
        .hint { margin-top: 20px; font-size: 0.85em; color: #7f8c8d; background: #f8fafc; padding: 10px; border-radius: 4px; }
    </style>
</head>
<body>
<div class="card">
    <h2>Авторизация</h2>

    <#if errorMessage??>
        <div class="alert-error">${errorMessage}</div>
    </#if>
    <#if logoutMessage??>
        <div class="alert-success">${logoutMessage}</div>
    </#if>
    <#if registeredMessage??>
        <div class="alert-success">${registeredMessage}</div>
    </#if>

    <form action="${rc.contextPath}/login" method="post">
        <div class="form-group">
            <label>Логин:</label>
            <input type="text" name="username" required autofocus/>
        </div>
        <div class="form-group">
            <label>Пароль:</label>
            <input type="password" name="password" required/>
        </div>
        <button type="submit" class="btn-login">Войти</button>
    </form>

    <a class="register-link" href="${rc.contextPath}/register">Нет аккаунта? Зарегистрироваться</a>

    <div class="hint">
        <strong>Тестовые аккаунты:</strong><br/>
        • Админ: <code>admin</code> / <code>admin</code><br/>
        • Пользователь: <code>user</code> / <code>user</code>
    </div>
</div>
</body>
</html>