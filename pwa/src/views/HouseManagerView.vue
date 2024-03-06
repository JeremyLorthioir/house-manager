<script lang="ts">
export default {
  name: 'HouseManager',
  data() {
    return {
      tasks: []
    }
  },
  mounted() {
    fetch('http://localhost:8080/tasks')
      .then((res) => res.json())
      .then((data) => (this.tasks = data))
      .catch((err) => console.log(err))
  }
}
</script>

<template>
  <v-table :data="tasks" class="table">
    <thead>
      <th>Libellé</th>
      <th>Type</th>
      <th>Récurence</th>
    </thead>
    <tbody>
      <tr v-for="(task, index) in tasks" :key="index">
        <td>{{ task.name }}</td>
        <td>
          <span class="type-capsule">{{ task.type }}</span>
        </td>
        <td>{{ task.recurrence.description }}</td>
      </tr>
    </tbody>
  </v-table>
</template>

<style>
/* Styles CSS pour un tableau moderne */
.table {
  width: 100%;
  border-spacing: 0;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
.table th,
.table td {
  border-bottom: 1px solid #e0e0e0;
  padding: 12px;
  text-align: left;
}
.table th {
  color: var(--light);
  background-color: var(--dark);
  font-weight: 600;
}
.table tr {
  background-color: #f1f5f9;
}
.table tr:nth-child(even) {
  background-color: #fafafa;
}
/* Style pour les capsules */
.type-capsule {
  font-weight: bold;
  display: inline-block;
  background-color: var(--primary-alt);
  color: var(--light);
  border-radius: 20px;
  padding: 5px 10px;
  margin: 2px;
}
</style>
