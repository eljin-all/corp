<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список телевизоров</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f4f6f9; margin: 0; padding: 30px; color: #333; }
        .container { background: #fff; max-width: 950px; margin: 0 auto; padding: 25px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        .alert-success { background: #eefaf3; color: #27ae60; border: 1px solid #a3e4d7; padding: 12px 16px; border-radius: 6px; margin-bottom: 15px; font-weight: 500; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 12px; border-bottom: 1px solid #e1e8ed; text-align: left; }
        th { background: #f8fafc; color: #2c3e50; }
        tr:hover { background: #fdfdfd; }
        .actions { display: flex; align-items: center; gap: 10px; }
        .actions a { text-decoration: none; font-weight: 600; }
        .edit-link { color: #f39c12; }
        .delete-link { color: #e74c3c; }
        .btn-buy { background: #27ae60; color: white; border: none; padding: 6px 12px; border-radius: 4px; cursor: pointer; font-weight: 600; font-size: 14px; }
        .btn-buy:hover { background: #219150; }
        .nav-link { display: inline-block; margin-top: 20px; text-decoration: none; color: #3498db; font-weight: 500; margin-right: 15px; }
    </style>
</head>
<body>
<div class="container">
    <h2>Список доступных телевизоров</h2>

    <#if successMessage??>
        <div class="alert-success">${successMessage}</div>
    </#if>

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
                    <form action="${rc.contextPath}/tvs/buy" method="post" style="margin:0;">
                        <input type="hidden" name="id" value="${item.id}"/>
                        <button type="submit" class="btn-buy">Купить</button>
                    </form>
                    <a class="edit-link" href="${rc.contextPath}/tvs/edit?id=${item.id}">Редактировать</a>
                    <a class="delete-link" href="${rc.contextPath}/tvs/delete?id=${item.id}">Удалить</a>
                </td>
            </tr>
        <#else>
            <tr><td colspan="7">Все телевизоры раскуплены или записей пока нет</td></tr>
        </#list>
        </tbody>
    </table>
    <a class="nav-link" href="${rc.contextPath}/tvs/add">+ Добавить товар</a>
    <a class="nav-link" href="${rc.contextPath}/">« На главную</a>
</div>
</body>
</html>