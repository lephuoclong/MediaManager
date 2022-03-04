/** @format */

import { Dropdown, Stack, Text } from "@fluentui/react";
import React, { useEffect, useState } from "react";
import GridComponent from "../../../components/layoutComponent/GridComponent";
import ListComponent from "../../../components/layoutComponent/ListComponent";
import SpinnerLoading from "../../../components/multipleComponents/SpinnerLoading";
import { TEXT_TITLE, WH_100 } from "../../../constants/styles";

const dropdownStyles = {
  caretDown: { display: "none" },
  root: { minWidth: 100, marginRight: 30 },
};

const options = [
  {
    key: "list",
    text: "List",
  },
  {
    key: "grid",
    text: "Grid",
  },
];

export default function Garbage() {
  const [isLoadingLayout, setIsLoadingLayout] = useState(true);
  const [isListLayout, setIsListLayout] = useState(true);

  useEffect(() => {
    setTimeout(() => {
      setIsLoadingLayout(false);
    }, 2000);
  }, []);

  const _changeLayout = (_event, value) => {
    setIsListLayout(value.key === "list");
  };

  return (
    <Stack styles={WH_100}>
      <Stack horizontal verticalAlign='center' horizontalAlign='space-between'>
        <Text variant='xLarge' styles={TEXT_TITLE}>
          Garbage
        </Text>
        <Dropdown
          placeholder='Select an option'
          options={options}
          styles={dropdownStyles}
          onChange={_changeLayout}
        />
      </Stack>

      {isLoadingLayout ? (
        <SpinnerLoading />
      ) : (
        <Stack styles={WH_100}>
          {isListLayout ? <ListComponent /> : <GridComponent />}
        </Stack>
      )}
    </Stack>
  );
}
