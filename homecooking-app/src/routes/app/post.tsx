import {NewPostForm} from '@/components/NewPostForm'
import {createFileRoute} from '@tanstack/react-router'

export const Route = createFileRoute('/app/post')({
    component: RouteComponent,
})

function RouteComponent() {
    return <div>
        <h1>Create a new post</h1>
        <NewPostForm/>
    </div>
}
