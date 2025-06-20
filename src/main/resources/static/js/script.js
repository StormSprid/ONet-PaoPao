let selectedCells = [];
let  lives = 3;
function renderBoard(board) {
    const boardDiv = document.getElementById("board");
    boardDiv.innerHTML = ""; // очистить поле

    let hasAliveCells = false; // флаг наличия живых ячеек

    board.forEach((row, y) => {
        row.forEach((cell, x) => {
            const cellDiv = document.createElement("div");
            cellDiv.className = "cell";
            if (cell.id === -1) {
                cellDiv.style.backgroundColor = "black";
            } else {
                cellDiv.textContent = cell.id;
                hasAliveCells = true; // нашли живую клетку
            }

            cellDiv.onclick = () => onCellClick(cellDiv, { ...cell, x, y }, x, y);
            boardDiv.appendChild(cellDiv);
        });
    });

    // ✅ Если нет живых клеток — победа!
    if (!hasAliveCells) {
        setTimeout(() => {
            window.location.href = "/victory.html"; // ← путь к странице победы
        }, 500); // небольшая задержка, чтобы игрок успел увидеть пустое поле
    }
}
function updateLivesText() {
    const livesText = document.getElementById("lives");
    livesText.textContent = `❤️ Lives: ${lives}`;
}

document.getElementById("restartBtn").addEventListener("click", () => {
    if (lives <= 0) {
        alert("😵 У вас нет жизней! Обновите страницу для новой игры.");
        return;
    }

    fetch('/api/restart', { method: 'POST' })
        .then(res => res.json())
        .then(success => {
            if (success) {
                lives--;
                updateLivesText();
                console.log("✅ Доска успешно перезапущена");
                fetch('/api/board')
                    .then(res => res.json())
                    .then(renderBoard);
            } else {
                console.log("❌ Не удалось перезапустить доску");
            }
        })
        .catch(err => console.error("Ошибка при рестарте:", err));
});


// Обработка клика по ячейке
function onCellClick(div, cell, x, y) {
    if (lives <= 0) {
        alert("😵 Вы проиграли! Нажмите рестарт.");
        return;
    }

    if (selectedCells.length >= 2) {
        clearSelection();
    }

    div.classList.add('selected');
    selectedCells.push({ cell, element: div });

    if (selectedCells.length === 2) {
        const [a, b] = selectedCells;
        sendKillRequest(a, b); // <== вот оно
    }
}


// Сброс выбора
function clearSelection() {
    selectedCells.forEach(({ element }) => element.classList.remove('selected'));
    selectedCells = [];
}

function sendKillRequest(a, b) {
    fetch('/api/kill', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify([a.cell, b.cell])
    })
        .then(res => res.json())
        .then(success => {
            if (success) {
                console.log('✅ Успешно удалено! Перерисовываем доску...');
                fetch('/api/board')
                    .then(res => res.json())
                    .then(renderBoard);
            } else {
                console.log('❌ Эти клетки нельзя соединить');
            }
            clearSelection(); // очищаем выбор независимо от успеха
        })
        .catch(err => console.error('Ошибка:', err));
}



// Слушатель клика вне доски
document.addEventListener('click', (e) => {
    // если клик вне .cell — сбрасываем выбор
    if (!e.target.classList.contains('cell')) {
        clearSelection();
    }
});

// Загрузка доски
fetch('/api/board')
    .then(res => res.json())
    .then(renderBoard);
