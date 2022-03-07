/** @format */

import React, { useState } from "react";
import PropTypes from "prop-types";
import { Icon } from "@fluentui/react";
import EmptyList from "../../../components/layoutComponent/EmptyList";
import { WH_100 } from "../../../constants/styles";
import { LIGHT_THEME, ROWS_PER_PAGE } from "../../../constants";
import ContentFolderList from "./components/ContentFolderList";

const emptyImageStyle = {
  root: {
    fill: LIGHT_THEME.palette.themePrimary,
  },
};

export default function MyFolderLayout(props) {
  const { isEmptyItem } = props;

  const [pagination, setPagination] = useState({
    page: 0,
    pageSize: ROWS_PER_PAGE.FIVE,
    totalPages: 0,
  });

  const _onChangePageIndex = page => {
    setPagination({ ...pagination, page });
  };

  const _onChangePageSize = pageSize => {
    setPagination({ ...pagination, page: 0, pageSize });
  };

  if (isEmptyItem) {
    return (
      <EmptyList styles={WH_100} title=' This folder is empty'>
        <Icon iconName='not-found-image' styles={emptyImageStyle} />
      </EmptyList>
    );
  }
  return (
    <ContentFolderList
      isPagination
      pagingOptions={{
        ...pagination,
        onChangePageSize: _onChangePageSize,
        onChangePageIndex: _onChangePageIndex,
      }}></ContentFolderList>
  );
}
MyFolderLayout.propTypes = {
  isEmptyItem: PropTypes.bool.isRequired,
};
