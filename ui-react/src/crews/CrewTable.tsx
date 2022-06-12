import {
  TableContainer,
  Paper,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
} from "@mui/material";
import { FC, useMemo } from "react";
import { useQuery } from "react-query";
import { useApi } from "../ApiContext";
import { Crew } from "../generated";

const CrewItem: FC<{ crew: Crew }> = ({ crew }) => {
  return (
    <TableRow
      key={crew.id}
      sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
    >
      <TableCell component="th" scope="row">
        {crew.name}
      </TableCell>
      <TableCell align="right">{crew.capacity}</TableCell>
      <TableCell align="right">
        {Array.from(crew.assignedTreasures.values())
          .map((t) => t.name + " (" + t.size + ")")
          .join(" ,")}
      </TableCell>
    </TableRow>
  );
};

const CrewHead: FC = () => {
  return (
    <TableHead>
      <TableRow>
        <TableCell>Crew Name</TableCell>
        <TableCell align="right">Capacity</TableCell>
        <TableCell align="right">Assigned Treasures</TableCell>
      </TableRow>
    </TableHead>
  );
};

export const CrewTable: FC = () => {
  const { crewApi } = useApi();
  const crewQuery = useQuery("crews", () => crewApi.getCrews());
  const crews = useMemo(
    () => crewQuery.data?.sort((a, b) => a.name.localeCompare(b.name)) ?? [],
    [crewQuery.data]
  );

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650, boxShadow: 20 }} aria-label="simple table">
        <CrewHead />
        <TableBody>
          {crews.map((crew) => (
            <CrewItem crew={crew} />
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};
