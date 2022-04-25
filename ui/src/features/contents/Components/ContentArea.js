/** @format */

import { Icon, Pivot, PivotItem, Stack, Text } from "@fluentui/react";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import {
  LIGHT_THEME,
  NAV_GROUPS,
  PAGE_PATHS,
  ROOT_FOLDER_ID,
  ROWS_PER_PAGE,
} from "../../../constants";
import { TEXT_TITLE, WH_100 } from "../../../constants/styles";
import { BREAKPOINTS_RESPONSIVE } from "../../../constants/SVGTheme";
import LoadingPage from "../../../components/multipleComponents/LoadingPage";

import DirectoryApi from "../../../API/ModuleAPI/DirectoryApi";
import EmptyContent from "../../../components/layoutComponent/EmptyContent";
import ContentFolder from "./ContentFolder";

const displayTabStyles = {
  root: {
    ...WH_100.root,
    display: "flex",
  },
};

const pivotSettingStyles = {
  root: {
    marginBottom: 29,
    [BREAKPOINTS_RESPONSIVE.mdDown]: {
      display: "flex",
      justifyContent: "center",
    },
  },
  linkIsSelected: {
    borderTop: `3px solid ${LIGHT_THEME.palette.themePrimary}`,
    color: `${LIGHT_THEME.palette.black}`,
    borderRadius: `0 !important`,
  },
  link: {
    marginRight: 8,
    minWidth: 100,
    maxWidth: 200,
    width: "100%",
    backgroundColor: `${LIGHT_THEME.palette.gray}`,
    "&:hover": {
      backgroundColor: `${LIGHT_THEME.palette.gray}`,
    },
    [BREAKPOINTS_RESPONSIVE.mdDown]: {
      width: "25%",
    },
  },
  itemContainer: {
    height: "calc(100vh - 220px)",
  },
};

export default function ContentArea() {
  const { pathPage } = useParams();

  const [rootFolders, setRootFolders] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [pagination, setPagination] = useState({
    page: 0,
    totalPages: 0,
    pageSize: ROWS_PER_PAGE.TEN,
  });

  const pageContent = NAV_GROUPS.find(
    nav => nav.key === (pathPage || PAGE_PATHS.MY_FOLDER)
  );

  const _getRootFolders = async () => {
    const rootFolderResult = await DirectoryApi.getFolderByParentId({
      parentId: ROOT_FOLDER_ID,
      ...pagination,
    });
    if (rootFolderResult.isAxiosError) {
      window.alert(rootFolderResult);
    } else {
      setRootFolders(rootFolderResult.data.items);
      setPagination({ ...pagination, totalPages: rootFolderResult.totalPages });
    }
    setIsLoading(false);
  };

  useEffect(() => {
    _getRootFolders();
  }, []);

  if (pathPage === PAGE_PATHS.GARBAGE) {
    return <Stack>thùng rác</Stack>;
  }

  return (
    <Stack styles={displayTabStyles}>
      <Text variant='xLarge' styles={TEXT_TITLE}>
        {pageContent.name}
      </Text>
      {isLoading ? (
        <LoadingPage />
      ) : rootFolders?.length > 0 ? (
        <Pivot
          aria-label='Basic Pivot Example'
          defaultSelectedKey={rootFolders[0].id}
          styles={pivotSettingStyles}>
          {rootFolders?.map((folder, key) => (
            <PivotItem
              itemKey={folder.id}
              key={key}
              headerText={folder.name}
              style={{ height: "100%" }}>
              <ContentFolder folder={folder} />
            </PivotItem>
          ))}
        </Pivot>
      ) : (
        <EmptyContent />
      )}
    </Stack>
  );
}
