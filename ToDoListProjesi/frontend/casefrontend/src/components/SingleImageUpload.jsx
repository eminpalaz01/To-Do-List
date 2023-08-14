import React, { useState } from 'react';
import { Button, IconButton, Paper } from '@mui/material';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import DeleteIcon from '@mui/icons-material/Delete';

const SingleImageUpload = ({ onImageSelect }) => {
  const [selectedImage, setSelectedImage] = useState(null);

  const handleImageSelect = (event) => {
    const image = event.target.files[0];
    setSelectedImage(image);
    onImageSelect(image);
  };

  const handleRemoveImage = () => {
    setSelectedImage(null);
    onImageSelect(null);
  };

  return (
    <Paper elevation={3} style={{ padding: '20px' }}>
      {selectedImage ? (
        <div style={{ display: 'flex', alignItems: 'center' }}>
          <IconButton onClick={handleRemoveImage}>
            <DeleteIcon />
          </IconButton>
          <div>{selectedImage.name}</div>
        </div>
      ) : (
        <label htmlFor="image-upload">
          <input
            id="image-upload"
            type="file"
            accept="image/*"
            style={{ display: 'none' }}
            onChange={handleImageSelect}
          />
          <Button
            variant="contained"
            component="span"
            startIcon={<CloudUploadIcon />}
          >
            Resim Seç
          </Button>
        </label>
      )}
    </Paper>
  );
};

export default SingleImageUpload;