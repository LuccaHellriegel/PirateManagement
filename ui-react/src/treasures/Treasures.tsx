import { FC } from "react";
import { useQuery } from "react-query";
import { useApi } from "../ApiContext";

export const Treasures: FC = () => {
  const { treasureApi } = useApi();
  const treasureQuery = useQuery("treasures", () => treasureApi.getTreasures());

  return (
    <div>
      Treasures:
      <br />
      {JSON.stringify(treasureQuery.data)}
    </div>
  );
};
