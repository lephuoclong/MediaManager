/** @format */

import React from "react";
import CustomDetailList from "../../../../components/layoutComponent/CustomDetailList";

export default function ContentFolderList(props) {
  const { pagingOptions } = props;
  return <CustomDetailList isPagination pagingOptions={pagingOptions} />;
}
