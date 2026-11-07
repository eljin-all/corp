<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Управление каталогом ТВ</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f4f6f9; margin: 0; padding: 40px; color: #333; }
        .card { background: #fff; max-width: 650px; margin: 0 auto; padding: 30px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        .top-bar { display: flex; justify-content: space-between; align-items: center; background: #f8fafc; padding: 12px 16px; border-radius: 6px; margin-bottom: 20px; border: 1px solid #e1e8ed; }
        .role-badge { background: #e8f4fd; color: #2980b9; padding: 3px 8px; border-radius: 4px; font-size: 0.85em; font-weight: 600; margin-left: 6px; }
        .btn-logout { background: #e74c3c; color: white; border: none; padding: 8px 14px; border-radius: 4px; cursor: pointer; font-weight: 500; }
        .btn-logout:hover { background: #c0392b; }
        h1 { color: #2c3e50; border-bottom: 2px solid #3498db; padding-bottom: 12px; margin-top: 0; }
        .menu { list-style: none; padding: 0; margin-top: 25px; }
        .menu li { margin: 12px 0; }
        .btn { display: inline-block; width: 90%; text-decoration: none; background: #3498db; color: white; padding: 12px 16px; border-radius: 6px; font-weight: 500; transition: 0.2s; text-align: center; }
        .btn:hover { background: #2980b9; }
        .btn-admin { background: #27ae60; }
        .btn-admin:hover { background: #219150; }
    </style>
</head>
<body>
<div class="card">
    <div class="top-bar">
        <div>
            Здравствуйте, <strong><@security.authentication property="principal.username" /></strong>!
            <@security.authorize access="hasRole('ROLE_ADMIN')">
                <span class="role-badge">Администратор</span>
            </@security.authorize>
            <@security.authorize access="hasRole('ROLE_USER') and !hasRole('ROLE_ADMIN')">
                <span class="role-badge">Пользователь</span>
            </@security.authorize>
        </div>
        <form action="${rc.contextPath}/logout" method="post" style="margin:0;">
            <button type="submit" class="btn-logout">Выйти</button>
        </form>
    </div>

    <h1>Каталог Телевизоров</h1>
    <p>Добро пожаловать в защищенное веб-приложение на базе Spring MVC и Spring Security.</p>

    <ul class="menu">
        <li><a class="btn" href="${rc.contextPath}/tvs">1. Просмотр всех телевизоров</a></li>
        <@security.authorize access="hasRole('ROLE_ADMIN')">
            <li><a class="btn btn-admin" href="${rc.contextPath}/tvs/add">2. Добавить новый телевизор (только Admin)</a></li>
        </@security.authorize>
        <li><a class="btn" href="${rc.contextPath}/tvs/search">3. Поиск телевизоров по цене</a></li>
    </ul>
</div>
</body>
</html>