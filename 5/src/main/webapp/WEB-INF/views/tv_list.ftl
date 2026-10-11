<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список телевизоров</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f4f6f9; margin: 0; padding: 30px; color: #333; }
        .container { background: #fff; max-width: 900px; margin: 0 auto; padding: 25px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 12px; border-bottom: 1px solid #e1e8ed; text-align: left; }
        th { background: #f8fafc; color: #2c3e50; }
        tr:hover { background: #fdfdfd; }
        .actions a { margin-right: 10px; text-decoration: none; font-weight: 600; }
        .edit-link { color: #f39c12; }
        .delete-link { color: #e74c3c; }
        .nav-link { display: inline-block; margin-top: 20px; text-decoration: none; color: #3498db; font-weight: 500; margin-right: 15px; }
    </style>
</head>
<body>
<div class="container">
    <h2>Список телевизоров в наличии</h2>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Бренд</th>
            <th>Модель</th>
            <th>Экран</th>
            <th>Диагональ</th>
            <th>Цена (руб.)</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <#list tvs as item>
            <tr>
                <td>${item.id}</td>
                <td>${item.brand}</td>
                <td>${item.model}</td>
                <td>${item.screenTechnology}</td>
                <td>${item.screenDiagonal}"</td>
                <td>${item.price?string["0.00"]}</td>
                <td class="actions">
                    <a class="edit-link" href="${rc.contextPath}/tvs/edit?id=${item.id}">Редактировать</a>
                    <a class="delete-link" href="${rc.contextPath}/tvs/delete?id=${item.id}">Удалить</a>
                </td>
            </tr>
        <#else>
            <tr><td colspan="7">Записей пока нет</td></tr>
        </#list>
        </tbody>
    </table>
    <a class="nav-link" href="${rc.contextPath}/tvs/add">+ Добавить товар</a>
    <a class="nav-link" href="${rc.contextPath}/">« На главную</a>
</div>
</body>
</html>