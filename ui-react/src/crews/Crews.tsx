import { FC } from "react";
import { useQuery } from "react-query";
import { useApi } from "../ApiContext";
import { CrewTable } from "./CrewTable";

export const Crews: FC = () => {
  return (
    <div>
      Crews:
      <br />
      <CrewTable />
    </div>
  );
};
