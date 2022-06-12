import React, { FC } from "react";
import { CrewControllerApi, TreasureControllerApi } from "./generated";

const ApiContext = React.createContext<{
  crewApi: CrewControllerApi;
  treasureApi: TreasureControllerApi;
  //@ts-ignore
}>(undefined);

export const ApiProvider: FC<{ children?: React.ReactNode }> = ({
  children,
}) => {
  const crewApi = new CrewControllerApi();
  const treasureApi = new TreasureControllerApi();

  return (
    <ApiContext.Provider value={{ crewApi, treasureApi }}>
      {children}
    </ApiContext.Provider>
  );
};

export const useApi = () => React.useContext(ApiContext);
