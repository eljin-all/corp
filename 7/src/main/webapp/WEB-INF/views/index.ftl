<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Управление каталогом ТВ</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f4f6f9; margin: 0; padding: 40px; color: #333; }
        .card { background: #fff; max-width: 650px; margin: 0 auto; padding: 30px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        h1 { color: #2c3e50; border-bottom: 2px solid #3498db; padding-bottom: 12px; }
        .menu { list-style: none; padding: 0; margin-top: 25px; }
        .menu li { margin: 12px 0; }
        .btn { display: inline-block; width: 90%; text-decoration: none; background: #3498db; color: white; padding: 12px 16px; border-radius: 6px; font-weight: 500; transition: 0.2s; text-align: center; }
        .btn:hover { background: #2980b9; }
    </style>
</head>
<body>
<div class="card">
    <h1>Каталог Телевизоров</h1>
    <p>Добро пожаловать в веб-приложение на базе Spring MVC для управления базой данных товаров.</p>
    <ul class="menu">
        <li><a class="btn" href="${rc.contextPath}/tvs">1. Просмотр всех телевизоров</a></li>
        <li><a class="btn" href="${rc.contextPath}/tvs/add">2. Добавить новый телевизор</a></li>
        <li><a class="btn" href="${rc.contextPath}/tvs/search">3. Поиск телевизоров по цене</a></li>
    </ul>
</div>
</body>
</html>