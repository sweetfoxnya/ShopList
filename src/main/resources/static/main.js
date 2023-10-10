window.onload = async function () {
    const response = await fetch("/shopList/", {
        method: "GET", headers: {"Accept": "application/json"}
    });
    if (response.ok) {
        let list = await response.json();
        for (let product of list) {
            add_product(product);
        }

        $(".delete").click(async (event) => {
            const response = await fetch(`/shopList/${event.target.id}`, {
                method: "DELETE", headers: {"Accept": "application/json"}
            });
            if (response.ok) {
                window.location.href = '/';
            }
        });
        $(".update").click(async (event) => {
            await fetch(`/shopList/${event.target.id}`, {
                method: "PUT", headers: {"Accept": "application/json"}
            });
        });
    }

    function add_product(product) {
        let products = document.getElementById("products");
        let child = document.createElement("div")
        child.innerHTML += `<input type="checkbox" class="update" id="${product.id}" ${product.bought ? 'checked' : ''} value="${product.bought}">`
        child.innerHTML += `${product.name} `;
        child.innerHTML += `<input type="button" class="delete" id="${product.id}" value="delete">`;
        products.appendChild(child);
    }
};