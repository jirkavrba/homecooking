import {Button, FileUpload, Input, Stack, Textarea, VStack} from "@chakra-ui/react";
import {HiUpload} from "react-icons/hi";

export const NewPostForm = () => {
    return (
        <VStack alignItems="stretch">
            <FileUpload.Root alignSelf="stretch">
                <FileUpload.Dropzone width="full">
                    <FileUpload.DropzoneContent>
                        <h1>Vybrat obrázek</h1>
                    </FileUpload.DropzoneContent>
                </FileUpload.Dropzone>
            </FileUpload.Root>

            <Input type="text" name="title" placeholder="Název" required/>
            <Textarea></Textarea>
        </VStack>
    )
};