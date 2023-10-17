<script>
import axios from "axios";

export default {
  data() {
    return {
      urlApi: "http://localhost:8080/api/pizze",
      pizze: {},
      newPizza: {
        name: "",
        description: "",
        photo_url: "",
        price: null,
      },
      searchText: "",
    };
  },
  methods: {
    getAllPizzas() {
      axios
        .get(this.urlApi)
        .then((response) => {
          console.log(response.data);
          this.pizze = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    storePizza() {
      axios
        .post(this.urlApi, this.newPizza)
        .then((res) => {
          const data = res.data;
          console.log(res);

          this.clearForm();
          this.getAllPizzas();
        })
        .catch((err) => console.log(err));
    },
    clearForm() {
      return (this.newPizza = {
        name: "",
        description: "",
        photo_url: "",
        price: null,
      });
    },
    deletePizza(id) {
      axios
        .delete(this.urlApi + "/" + id, this.newPizza)
        .then((res) => {
          const data = res.data;
          this.getAllPizzas();

          console.log(res.data);
        })
        .catch((err) => console.log(err));
    },
  },
  computed: {
    searchPizza: function () {
      axios
        .get(this.urlApi + "?q=" + this.searchText)
        .then((response) => {
          console.log(response.data);
          this.pizze = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
  mounted() {
    this.getAllPizzas();
  },
};
</script>

<template>
  <main>
    <section>
      <h1 class="text-center pb-5">La mia pizzeria</h1>

      <label for="searchPizza">Search pizza by name</label>
      <input
        @input="searchPizza"
        class="mb-3"
        type="text"
        v-model="this.searchText"
      />

      <ol>
        <li v-for="pizza in pizze" :key="pizza.id">
          <img :src="pizza.photo_url" :alt="pizza.name" />

          <p>Name: {{ pizza.name }}</p>
          <p>Description: {{ pizza.description }}</p>
          <p>Price: {{ pizza.price }} euro</p>

          <button
            @click="deletePizza(pizza.id)"
            type="button"
            class="btn btn-danger mb-3"
          >
            Delete this pizza
          </button>
        </li>
      </ol>
    </section>

    <section class="py-5">
      <div class="container">
        <form @submit.prevent="storePizza">
          <h2 class="text-center">Create pizza</h2>

          <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              aria-describedby="helpId"
              placeholder=""
              v-model="this.newPizza.name"
            />
          </div>

          <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <input
              type="text"
              class="form-control"
              name="description"
              aria-describedby="helpId"
              placeholder=""
              v-model="this.newPizza.description"
            />
          </div>

          <div class="mb-3">
            <label for="photo_url" class="form-label">Photo</label>
            <input
              type="text"
              class="form-control"
              name="photo_url"
              aria-describedby="helpId"
              placeholder=""
              v-model="this.newPizza.photo_url"
            />
          </div>

          <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input
              type="number"
              class="form-control"
              name="price"
              aria-describedby="helpId"
              placeholder=""
              v-model="this.newPizza.price"
            />
          </div>

          <button type="submit" class="btn btn-primary me-3">Create</button>

          <button @click.prevent="clearForm" class="btn btn-warning">
            Clear form
          </button>
        </form>
      </div>
    </section>
  </main>
</template>

<style scoped>
img {
  max-width: 200px;
}
</style>
