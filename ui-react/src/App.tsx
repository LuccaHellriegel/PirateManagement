import { FC, useEffect, useState } from "react";
import { ApiProvider, useApi } from "./ApiContext";
import { Crew, Treasure } from "./generated";

const Cmp: FC = () => {
  const { treasureApi, crewApi } = useApi();
  const [treasures, setTreasures] = useState<Treasure[]>([]);
  const [crews, setCrews] = useState<Crew[]>([]);

  useEffect(() => {
    treasureApi.getAll().then((treasures) => setTreasures(treasures));
    //TODO: find out how to disalbe the numbering
    crewApi.getAll1().then((crews) => setCrews(crews));
  }, []);

  return (
    <div>
      {JSON.stringify(treasures)}
      {JSON.stringify(crews)}
    </div>
  );
};

function App() {
  return (
    <ApiProvider>
      <Cmp />
    </ApiProvider>
  );
}

export default App;
