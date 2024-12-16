const API_BASE_URL = "http://localhost:8080/tasks"; 


const taskInput = document.getElementById("taskInput");
const addTaskBtn = document.getElementById("addTaskBtn");
const taskList = document.getElementById("taskList");


addTaskBtn.addEventListener("click", async () => {
    const taskName = taskInput.value.trim();
    if (!taskName) return alert("Unesi ime zadatka!");
    
    try {
        const response = await fetch(API_BASE_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ description: taskName, completed: false })
        });
        if (response.ok) {
            loadTasks();
            taskInput.value = "";
        } else {
            alert("Greška prilikom dodavanja zadatka!");
        }
    } catch (error) {
        console.error("API Error:", error);
    }
});

async function loadTasks() {
    try {
        const response = await fetch(API_BASE_URL);
        const tasks = await response.json();
        taskList.innerHTML = "";
        tasks.forEach(task => renderTask(task));
    } catch (error) {
        console.error("API Error:", error);
    }
}

function renderTask(task) {
    const taskDiv = document.createElement("div");
    taskDiv.className = `task ${task.completed ? "completed" : ""}`;
    taskDiv.innerHTML = `
        <span>${task.description}</span>
        <div>
            <button class="complete-btn" onclick="toggleComplete(${task.id}, ${!task.completed})">
                ${task.completed ? "Odznači" : "Označi kao završeno"}
            </button>
            <button onclick="deleteTask(${task.id})">Obriši</button>
        </div>
    `;
    taskList.appendChild(taskDiv);
}

async function deleteTask(id) {
    try {
        const response = await fetch(`${API_BASE_URL}/${id}`, { method: "DELETE" });
        if (response.ok) loadTasks();
        else alert("Greška prilikom brisanja zadatka!");
    } catch (error) {
        console.error("API Error:", error);
    }
}

async function toggleComplete(id, completed) {
    try {
        const response = await fetch(`${API_BASE_URL}/${id}`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ completed })
        });
        if (response.ok) loadTasks();
        else alert("Greška prilikom izmjene zadatka!");
    } catch (error) {
        console.error("API Error:", error);
    }
}


loadTasks();
