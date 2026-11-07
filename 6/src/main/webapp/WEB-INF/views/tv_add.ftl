<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Добавление телевизора</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background: #f4f6f9; padding: 40px; }
        .card { background: white; max-width: 500px; margin: 0 auto; padding: 30px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        .form-group { margin-bottom: 16px; }
        label { display: block; margin-bottom: 6px; font-weight: 600; color: #34495e; }
        input[type="text"], input[type="number"] { width: 100%; padding: 10px; border: 1px solid #ccd1d9; border-radius: 4px; box-sizing: border-box; }
        .error { color: #e74c3c; font-size: 0.9em; margin-top: 4px; }
        .btn-submit { background: #2ecc71; color: white; border: none; padding: 10px 18px; border-radius: 4px; cursor: pointer; font-size: 16px; }
        .btn-submit:hover { background: #27ae60; }
        .back-link { display: inline-block; margin-top: 15px; color: #7f8c8d; text-decoration: none; }
    </style>
</head>
<body>
<div class="card">
    <h2>Новый телевизор</h2>
    <form action="${rc.contextPath}/tvs/add" method="post">
        <div class="form-group">
            <label>Бренд:</label>
            <@spring.bind "tv.brand"/>
            <input type="text" name="brand" value="${spring.status.value!?html}"/>
            <#list spring.status.errorMessages as error><div class="error">${error}</div></#list>
        </div>

        <div class="form-group">
            <label>Модель:</label>
            <@spring.bind "tv.model"/>
            <input type="text" name="model" value="${spring.status.value!?html}"/>
            <#list spring.status.errorMessages as error><div class="error">${error}</div></#list>
        </div>

        <div class="form-group">
            <label>Технология экрана:</label>
            <@spring.bind "tv.screenTechnology"/>
            <input type="text" name="screenTechnology" value="${spring.status.value!?html}"/>
            <#list spring.status.errorMessages as error><div class="error">${error}</div></#list>
        </div>

        <div class="form-group">
            <label>Диагональ (дюймы):</label>
            <@spring.bind "tv.screenDiagonal"/>
            <input type="number" step="0.1" name="screenDiagonal" value="${spring.status.value!?html}"/>
            <#list spring.status.errorMessages as error><div class="error">${error}</div></#list>
        </div>

        <div class="form-group">
            <label>Цена (руб.):</label>
            <@spring.bind "tv.price"/>
            <input type="number" step="0.01" name="price" value="${spring.status.value!?html}"/>
            <#list spring.status.errorMessages as error><div class="error">${error}</div></#list>
        </div>

        <button type="submit" class="btn-submit">Сохранить</button>
    </form>
    <a class="back-link" href="${rc.contextPath}/tvs">« Отмена и возврат к списку</a>
</div>
</body>
</html>