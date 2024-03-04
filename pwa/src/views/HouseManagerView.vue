
<script lang="ts">

export default {
  data() {
    return {
      tasks: [],
    };
  },
  mounted() {
    fetch('http://localhost:8080/tasks')
      .then((res) => res.json())
      .then(data => this.tasks = data)
      .catch(err => console.log(err));
  },
};
</script>


<template>
  <div class="post">
    <div v-if="tasks" class="content">
      <ul>
        <li v-for="task in tasks" :key="task.id">
          {{ task.name }}
        </li>
      </ul>
    </div>
    <div v-else class="loading">Loading...</div>

  </div>
</template>
