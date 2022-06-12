import { FC, useEffect, useState } from "react";
import { QueryClient, QueryClientProvider, useQuery } from "react-query";
import { ApiProvider, useApi } from "./ApiContext";
import { Crew } from "./generated";
//TODO: find out how to disable the API method numbering

const queryClient = new QueryClient();

const Cmp: FC = () => {
  const { treasureApi, crewApi } = useApi();
  const treasureQuery = useQuery("treasures", () => treasureApi.getAll());
  const crewQuery = useQuery("crews", () => crewApi.getAll1());

  return (
    <div>
      Treasures:
      <br />
      {JSON.stringify(treasureQuery.data)}
      <br />
      <br />
      Crews:
      <br />
      {JSON.stringify(crewQuery.data)}
    </div>
  );
};

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <ApiProvider>
        <Cmp />
      </ApiProvider>
    </QueryClientProvider>
  );
}

export default App;
