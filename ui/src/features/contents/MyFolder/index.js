/** @format */

import { Breadcrumb, Dropdown, Stack, Text } from "@fluentui/react";
import React, { useEffect, useState } from "react";
import GridComponent from "../../../components/layoutComponent/GridComponent";
import ListComponent from "../../../components/layoutComponent/ListComponent";
import SpinnerLoading from "../../../components/multipleComponents/SpinnerLoading";
import { itemsFolderTree } from "../../../constants/initialApi/items";
import { TEXT_TITLE, WH_100 } from "../../../constants/styles";
import MyFolderLayout from "./MyFolderLayout";

const dropdownStyles = {
  caretDown: { display: "none" },
  root: { minWidth: 100, marginRight: 30 },
};

const displayTabStyles = {
  root: {
    ...WH_100.root,
    display: "flex",
  },
};

const wrapContent = {
  root: {
    ...WH_100.root,
    display: "flex",
  },
};

const contentFolderStyles = {
  root: {
    overflowY: "auto",
    overflowX: "hidden",
    height: "calc(100vh - 194px)",
  },
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

export default function MyFolder() {
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
    <Stack styles={displayTabStyles}>
      <Stack horizontal verticalAlign='center' horizontalAlign='space-between'>
        <Text variant='xLarge' styles={TEXT_TITLE}>
          My Folder
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
        <Stack grow={1}>
          <Stack styles={wrapContent}>
            <Breadcrumb
              items={itemsFolderTree}
              maxDisplayedItems={5}
              overflowIndex={1}
            />
            <Stack grow={1} styles={contentFolderStyles}>
              <MyFolderLayout isEmptyItem={isListLayout} />
            </Stack>
          </Stack>
        </Stack>
      )}
    </Stack>
  );
}
