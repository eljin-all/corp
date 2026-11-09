<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Поиск телевизоров</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background: #f4f6f9; padding: 30px; }
        .container { background: white; max-width: 800px; margin: 0 auto; padding: 25px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        .search-box { display: flex; gap: 10px; margin: 20px 0; }
        input[type="number"] { padding: 10px; border: 1px solid #ccd1d9; border-radius: 4px; flex-grow: 1; }
        button { background: #3498db; color: white; border: none; padding: 10px 20px; border-radius: 4px; cursor: pointer; font-weight: 600; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border-bottom: 1px solid #eee; text-align: left; }
        th { background: #f8fafc; }
        .nav-link { display: inline-block; margin-top: 20px; text-decoration: none; color: #3498db; }
    </style>
</head>
<body>
<div class="container">
    <h2>Поиск телевизоров по бюджету</h2>
    <form action="${rc.contextPath}/tvs/search" method="get" class="search-box">
        <input type="number" step="100" name="maxPrice" placeholder="Введите максимальную цену..." value="${maxPrice!}"/>
        <button type="submit">Найти</button>
    </form>

    <#if tvs??>
        <h3>Результаты поиска (до ${maxPrice} руб.):</h3>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Бренд</th>
                <th>Модель</th>
                <th>Технология</th>
                <th>Диагональ</th>
                <th>Цена</th>
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
                    <td>${item.price} руб.</td>
                </tr>
            <#else>
                <tr><td colspan="6">Телевизоров дешевле указанной цены не найдено.</td></tr>
            </#list>
            </tbody>
        </table>
    </#if>

    <a class="nav-link" href="${rc.contextPath}/">« На главную</a>
</div>
</body>
</html>