/** @format */

import React, { useState } from "react";
import PropTypes from "prop-types";
import { Stack, TextField, Toggle } from "@fluentui/react";
import CustomButton from "../../../components/CustomButton";
import DirectoryApi from "../../../API/ModuleAPI/DirectoryApi";

const textFieldStyles = {
  field: {
    fontSize: 12,
    lineHeight: 14,
  },
  fieldGroup: {
    height: 40,
    padding: 0,
  },
};

export default function AddContentSchema(props) {
  const [options, setOption] = useState(true);
  const { directoryId, onDismiss, fileType, refreshDirectory } = props;
  const [folderName, setFolderName] = useState("");

  const _submitContent = async () => {
    const data = {
      parentId: directoryId,
      name: folderName,
      level: fileType,
    };

    const createFolder = await DirectoryApi.createFolder(data);

    if (createFolder.isAxiosError) {
      window.alert(createFolder.response.data.message, {
        title: `Error Create Directory!`,
      });
    } else {
      refreshDirectory();
      onDismiss();
    }
  };

  const _handelDismiss = () => {
    onDismiss();
  };

  const _selectFolderOrFile = value => {
    console.log(value);
    setOption(value);
  };

  const renderNameType = () => {
    if (Number(fileType) === 1) {
      return "Document";
    }
    if (Number(fileType) === 2) {
      return "Music";
    }
    if (Number(fileType) === 3) {
      return "Photo";
    }
    if (Number(fileType) === 4) {
      return "Movie";
    }
    return undefined;
  };

  const _handleFolderNameChange = value => {
    setFolderName(value);
  };

  return (
    <Stack>
      <Stack styles={{ root: { paddingBottom: 20 } }}>
        <Toggle
          label='Select folder or file'
          defaultChecked
          onText='Create Folder'
          offText={`Upload ${renderNameType()} File`}
          onChange={(_e, value) => _selectFolderOrFile(value)}
        />
        {options ? (
          <TextField
            autoFocus
            value={folderName}
            onChange={(_e, value) => _handleFolderNameChange(value)}
            styles={textFieldStyles}
          />
        ) : (
          <Stack>select file</Stack>
        )}
      </Stack>
      <Stack horizontal horizontalAlign='space-between'>
        <CustomButton text='Cancel' onClick={_handelDismiss} />
        <CustomButton primary text='Add Content' onClick={_submitContent} />
      </Stack>
    </Stack>
  );
}
AddContentSchema.propTypes = {
  directoryId: PropTypes.string,
  onDismiss: PropTypes.func.isRequired,
  fileType: PropTypes.number.isRequired,
  refreshDirectory: PropTypes.func.isRequired,
};
AddContentSchema.defaultProps = {
  directoryId: undefined,
};
