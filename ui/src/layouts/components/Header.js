/** @format */

import {
  makeStyles,
  Stack,
  Link,
  Image,
  ImageFit,
  IconButton,
  SearchBox,
  Persona,
  PersonaSize,
  ContextualMenu,
} from "@fluentui/react";
import React, { useRef, useState } from "react";
import CustomModal from "../../components/modals";
import { LIGHT_THEME, MODEL_NAME, PAGE_PATHS } from "../../constants";
import MenuLink from "./MenuLink";
const useStyles = makeStyles({
  largerHeader: {
    height: 60,
    width: "100vw",
    backgroundColor: `${LIGHT_THEME.palette.grayLight}`,
    borderBottom: `1px solid ${LIGHT_THEME.palette.gray}`,
    position: "fixed",
    zIndex: 10,
  },
  wrapHeader: {
    height: "100%",
    width: "100%",
    maxWidth: 1440,
    margin: "auto",
  },
});

const searchButtonStyles = {
  icon: {
    color: `${LIGHT_THEME.palette.neutralSecondaryAlt}`,
  },
  root: {
    width: 60,
    height: 60,
  },
};

export default function Header(props) {
  const classes = useStyles();
  const [modalName, setModalName] = useState("");
  const [isShowMenuPersonal, setIsShowMenuPersonal] = useState(false);

  const personalMenuRef = useRef(null);

  const menuPersonals = [
    {
      key: "profile",
      text: "Ba Long",
      onClick: () => window.alert("go to profile page"),
    },
    {
      key: "email",
      text: "balong@gmail.com",
      disabled: true,
    },
    {
      key: "setting",
      text: "settings",
      onClick: () => window.alert(`/${PAGE_PATHS.setting}`),
    },
    {
      key: "logout",
      text: "logout",
      onClick: () => window.alert("logout"),
    },
  ];

  const _toggleSearch = modelName => {
    setModalName(modelName);
  };

  const _disMissModal = () => {
    setModalName("");
  };

  const _onShowPersonaMenu = () => {
    setIsShowMenuPersonal(true);
  };

  const _onHidePersonaMenu = () => {
    setIsShowMenuPersonal(false);
  };
  return (
    <Stack className={classes.largerHeader}>
      <Stack
        className={`${classes.wrapHeader} ms-Grid-col ms-sm2 ms-md4 ms-lg2 ms-xl8`}
        grow
        horizontal
        horizontalAlign='space-between'
        verticalAlign='center'>
        <Stack horizontal verticalAlign='center'>
          <Link href='/'>
            <Image
              imageFit={ImageFit.contain}
              src='/img/logo-back.png'
              alt='logo'
            />
          </Link>
          <MenuLink />
        </Stack>
        <Stack horizontal verticalAlign='center'>
          <IconButton
            iconProps={{ iconName: "Search" }}
            title='Search'
            ariaLabel='Search'
            styles={searchButtonStyles}
            onClick={() => _toggleSearch(MODEL_NAME.SEARCH_HEADER_MODEL)}
          />
          <Persona
            text='Ba Long'
            ref={personalMenuRef}
            size={PersonaSize.size48}
            styles={{ root: { cursor: "pointer" } }}
            onClick={_onShowPersonaMenu}
          />
          <CustomModal
            isOpen={modalName === MODEL_NAME.SEARCH_HEADER_MODEL}
            onDismiss={_disMissModal}
            onPrimaryButtonClick={() => window.alert("okokok")}
            primaryButtonText='Search'
            isSubmitting
            title='Search'>
            <SearchBox
              placeholder='Search'
              // TODO: handle search box
              onSearch={newValue => console.log("value is " + newValue)}
            />
          </CustomModal>
          <ContextualMenu
            items={menuPersonals}
            hidden={!isShowMenuPersonal}
            target={personalMenuRef}
            shouldFocusOnMount={true}
            onDismiss={_onHidePersonaMenu}
          />
        </Stack>
      </Stack>
    </Stack>
  );
}
