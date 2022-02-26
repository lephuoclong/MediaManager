/** @format */

import { makeStyles, Stack, Link, Image, ImageFit } from "@fluentui/react";
import React from "react";
import { LIGHT_THEME } from "../../constants";
import MenuLink from "./MenuLink";
const useStyles = makeStyles({
  largerHeader: {
    height: 60,
    width: "100vw",
    backgroundColor: `${LIGHT_THEME.palette.grayLight}`,
    borderBottom: `1px solid ${LIGHT_THEME.palette.gray}`,
  },
  wrapHeader: {
    height: "100%",
    width: "100%",
    maxWidth: 1440,
    margin: "auto",
  },
});

export default function Header() {
  const classes = useStyles();
  return (
    <Stack className={classes.largerHeader}>
      <Stack
        className={`${classes.wrapHeader} ms-Grid-col ms-sm2 ms-md4 ms-lg2 ms-xl8`}
        grow
        horizontal
        verticalAlign='center'>
        <Link to='/'>
          <Image
            imageFit={ImageFit.contain}
            src='/img/logo-back.png'
            alt='logo'
          />
        </Link>
        <MenuLink />
        <Stack> search</Stack>
        <Stack> avatar </Stack>
      </Stack>
    </Stack>
  );
}
