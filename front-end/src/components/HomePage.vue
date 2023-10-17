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
            class="btn btn-primary mb-3"
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

          <label for="name">Name</label>
          <br />
          <input type="text" name="name" v-model="this.newPizza.name" />
          <br />
          <label for="description">Description</label>
          <br />
          <input
            type="text"
            name="description"
            v-model="this.newPizza.description"
          />
          <br />
          <label for="photo">Photo</label>
          <br />
          <input
            type="text"
            name="photo_url"
            v-model="this.newPizza.photo_url"
          />
          <br />
          <label for="price">Price</label>
          <br />
          <input type="number" name="price" v-model="this.newPizza.price" />
          <br />

          <button type="submit">Create</button>
          <button @click.prevent="clearForm">Clear form</button>
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
