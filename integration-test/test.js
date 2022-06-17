const assert = require("assert");

const baseUrl = "http://localhost:8080/api/v1";
const treasureBaseUrl = baseUrl + "/treasures/";
const crewBaseUrl = baseUrl + "/crews/";

const treasureKeys = ["name", "owner", "position", "size"];

function createEmptyTreasure(count, pos, size) {
  return {
    name: "treasure" + count,
    owner: "owner" + count,
    position: pos,
    size,
  };
}

function createPos(x, y) {
  return { x, y };
}

const crewKeys = ["name", "capacity", "usedCapacity"];

function createEmptyCrew(count, capcity) {
  return { name: "crew" + count, capcity, usedCapacity: 0 };
}

const treasures = [
  [1, createPos(1, 2), 10],
  [2, createPos(2, 3), 15],
  [3, createPos(3, 4), 20],
  [4, createPos(300, 400), 20],
].map((arr) => createEmptyTreasure(...arr));

const crews = [
  [1, 100],
  [2, 100],
  [3, 150],
].map((arr) => createEmptyCrew(...arr));

const headers = {
  "Content-Type": "application/json",
};

async function postTreasure(treasure) {
  return await fetch(treasureBaseUrl, {
    method: "POST",
    body: JSON.stringify(treasure),
    headers,
  });
}

async function postCrew(crew) {
  return await fetch(crewBaseUrl, {
    method: "POST",
    body: JSON.stringify(crew),
    headers,
  });
}

async function getAllTreasures() {
  return await fetch(treasureBaseUrl, { method: "GET" });
}

async function getAllCrews() {
  return await fetch(crewBaseUrl, { method: "GET" });
}

async function extract(response) {
  return { status: response.status, body: await response.json() };
}

function same(keys, originalEntity, dbEntity) {
  const filteredDBEntity = Object.fromEntries(
    Object.entries(dbEntity).filter((arr) => keys.includes(arr[0]))
  );

  assert.deepEqual(originalEntity, filteredDBEntity);
}

//TODO: validate that all the services can use the db (create on in one and use in another!)
//scripts for starting each service from the root dir

async function main() {
  //---CRUD---

  //create entities

  //TODO: sort by name
  // const treasurePostResults = await Promise.all(
  //   treasures.map((t) => postTreasure(t).catch(console.log).then(extract))
  // );
  // const treasurePostResultsCodes = Array.from(
  //   new Set(treasurePostResults.map((e) => e.status)).values()
  // );
  // assert.deepEqual(treasurePostResultsCodes, ["201"]);

  // const crewPostResults = await Promise.all(
  //   crews.map((t) => postCrew(t).catch(console.log).then(extract))
  // );
  //invalid POST

  //TODO: assign call of treasure1 and crew1

  console.log(await getAllTreasures().then(extract));
  //create all entites and validate body is the same (except id stuff)
  //get all entities and validate sorted body is the same (except id stuff)
  //---DOMAIN---
  //succesful assignment returns updated treasure (validate)
  //get treasure, still updated
  //get crew, also updated
  //error assignment returns 400
  //---VIEW---
  //get crew view, validate
  //get treasure view, validate
}
main();
