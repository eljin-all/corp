<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Поиск телевизоров по цене</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background: #f4f6f9; padding: 30px; }
        .container { background: white; max-width: 850px; margin: 0 auto; padding: 25px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        input[type="number"] { padding: 8px 12px; border: 1px solid #ccd1d9; border-radius: 4px; width: 200px; }
        .btn-search { background: #3498db; color: white; border: none; padding: 9px 16px; border-radius: 4px; cursor: pointer; }
        .btn-buy { background: #27ae60; color: white; border: none; padding: 6px 12px; border-radius: 4px; cursor: pointer; font-weight: 600; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border-bottom: 1px solid #e1e8ed; text-align: left; }
        th { background: #f8fafc; }
        .back-link { display: inline-block; margin-top: 20px; color: #3498db; text-decoration: none; }
    </style>
</head>
<body>
<div class="container">
    <h2>Поиск телевизоров по бюджету</h2>
    <form action="${rc.contextPath}/tvs/search" method="get">
        <label>Максимальная цена (руб.):</label>
        <input type="number" step="0.01" name="maxPrice" value="${(maxPrice?c)!''}" required/>
        <button type="submit" class="btn-search">Найти</button>
    </form>

    <#if tvs??>
        <h3>Результаты поиска (до ${maxPrice} руб.):</h3>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Бренд</th>
                <th>Модель</th>
                <th>Экран</th>
                <th>Диагональ</th>
                <th>Цена (руб.)</th>
                <th>Покупка</th>
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
                    <td>
                        <form action="${rc.contextPath}/tvs/buy" method="post" style="margin:0;">
                            <input type="hidden" name="id" value="${item.id}"/>
                            <button type="submit" class="btn-buy">Купить</button>
                        </form>
                    </td>
                </tr>
            <#else>
                <tr><td colspan="7">Телевизоры в данном бюджете не найдены</td></tr>
            </#list>
            </tbody>
        </table>
    </#if>

    <a class="back-link" href="${rc.contextPath}/">« На главную</a>
</div>
</body>
</html>