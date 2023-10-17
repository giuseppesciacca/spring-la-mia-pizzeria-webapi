<script>
import axios from "axios";

export default {
  data() {
    return {
      urlApi: "http://localhost:8080/api/pizze",
      pizze: {},
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
  },
  mounted() {
    this.getAllPizzas();
  },
};
</script>

<template>
  <main>
    <h1>La mia pizzeria</h1>

    <ol>
      <li v-for="pizza in pizze" :key="pizza.id">
        <img :src="pizza.photo_url" alt="pizza.name" />

        <p>Name: {{ pizza.name }}</p>
        <p>Description: {{ pizza.description }}</p>
        <p>Price: {{ pizza.price }} euro</p>
      </li>
    </ol>
  </main>
</template>

<style scoped>
img {
  max-width: 200px;
}
</style>
