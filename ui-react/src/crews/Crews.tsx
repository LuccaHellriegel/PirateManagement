import { FC } from "react";
import { useQuery } from "react-query";
import { useApi } from "../ApiContext";

export const Crews: FC = () => {
  const { crewApi } = useApi();
  const crewQuery = useQuery("crews", () => crewApi.getAll1());

  return (
    <div>
      Crews:
      <br />
      {JSON.stringify(crewQuery.data)}
    </div>
  );
};
