<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Просмотр телевизора #${tv.id}</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background: #f4f6f9; padding: 40px; color: #333; }
        .card { background: white; max-width: 500px; margin: 0 auto; padding: 30px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        h2 { color: #2c3e50; border-bottom: 2px solid #3498db; padding-bottom: 10px; margin-top: 0; }
        .prop { margin: 12px 0; font-size: 16px; }
        .prop strong { color: #34495e; display: inline-block; width: 180px; }
        .actions { margin-top: 25px; display: flex; gap: 12px; }
        .btn { text-decoration: none; padding: 10px 16px; border-radius: 4px; color: white; font-weight: 500; }
        .btn-edit { background: #f39c12; }
        .btn-delete { background: #e74c3c; }
        .btn-back { background: #7f8c8d; }
    </style>
</head>
<body>
<div class="card">
    <h2>Телевизор #${tv.id}</h2>
    <div class="prop"><strong>Бренд:</strong> ${tv.brand}</div>
    <div class="prop"><strong>Модель:</strong> ${tv.model}</div>
    <div class="prop"><strong>Технология экрана:</strong> ${tv.screenTechnology}</div>
    <div class="prop"><strong>Диагональ:</strong> ${tv.screenDiagonal}"</div>
    <div class="prop"><strong>Цена:</strong> ${tv.price?string["0.00"]} руб.</div>

    <div class="actions">
        <a class="btn btn-edit" href="${rc.contextPath}/tvs/${tv.id}/edit">Редактировать</a>
        <a class="btn btn-delete" href="${rc.contextPath}/tvs/${tv.id}/delete">Удалить</a>
        <a class="btn btn-back" href="${rc.contextPath}/tvs">« К списку</a>
    </div>
</div>
</body>
</html>