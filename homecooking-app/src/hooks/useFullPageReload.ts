export const useFullPageReload = (path: string) => {
    return () => {
        window.location.href = path;
    };
};