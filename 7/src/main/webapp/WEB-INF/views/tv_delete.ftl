<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Удаление телевизора</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background: #f4f6f9; padding: 40px; }
        .card { background: white; max-width: 500px; margin: 0 auto; padding: 30px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); text-align: center; }
        .info { background: #fdf2f2; padding: 15px; border-radius: 6px; margin: 20px 0; color: #c0392b; font-weight: 500; text-align: left; }
        .btn-delete { background: #e74c3c; color: white; border: none; padding: 10px 20px; border-radius: 4px; cursor: pointer; font-size: 16px; font-weight: 600; }
        .btn-delete:hover { background: #c0392b; }
        .cancel-link { display: inline-block; margin-top: 15px; color: #7f8c8d; text-decoration: none; margin-left: 15px; }
    </style>
</head>
<body>
<div class="card">
    <h2>Удаление телевизора</h2>
    <p>Вы действительно хотите удалить следующую запись из базы данных?</p>
    <div class="info">
        <p><strong>ID:</strong> ${tv.id}</p>
        <p><strong>Бренд:</strong> ${tv.brand}</p>
        <p><strong>Модель:</strong> ${tv.model}</p>
        <p><strong>Цена:</strong> ${tv.price} руб.</p>
    </div>
    <form action="${rc.contextPath}/tvs/${tv.id}/delete" method="post" style="display:inline;">
        <button type="submit" class="btn-delete">Подтвердить удаление</button>
    </form>
    <a class="cancel-link" href="${rc.contextPath}/tvs">Отмена</a>
</div>
</body>
</html>